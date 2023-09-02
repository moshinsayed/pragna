//webkitURL is deprecated but nevertheless
URL = window.URL || window.webkitURL;

var gumStream; 						//stream from getUserMedia()
var rec; 							//Recorder.js object
var input; 							//MediaStreamAudioSourceNode we'll be recording

// shim for AudioContext when it's not avb. 
var AudioContext = window.AudioContext || window.webkitAudioContext;
var audioContext //audio context to help us record

var recordButton = document.getElementById("recordButton");
var stopButton = document.getElementById("stopButton");
var pauseButton = document.getElementById("pauseButton");

//add events to those 2 buttons
recordButton.addEventListener("click", startRecording);
stopButton.addEventListener("click", stopRecording);
pauseButton.addEventListener("click", pauseRecording);

function startRecording() {
	console.log("recordButton clicked");

	/*
		Simple constraints object, for more advanced audio features see
		https://addpipe.com/blog/audio-constraints-getusermedia/
	*/
    
    var constraints = { audio: true, video:false }

 	/*
    	Disable the record button until we get a success or fail from getUserMedia() 
	*/

//	recordButton.disabled = true;
	stopButton.disabled = false;
	document.getElementById("recordButton").style.display = "none";
  	document.getElementById("stopButton").style.display = "inline-block";
  	$("#stopButton").addClass("button-animate");
//	pauseButton.disabled = false

	/*
    	We're using the standard promise based getUserMedia() 
    	https://developer.mozilla.org/en-US/docs/Web/API/MediaDevices/getUserMedia
	*/

	navigator.mediaDevices.getUserMedia(constraints).then(function(stream) {
		console.log("getUserMedia() success, stream created, initializing Recorder.js ...");
		
		/*
			create an audio context after getUserMedia is called
			sampleRate might change after getUserMedia is called, like it does on macOS when recording through AirPods
			the sampleRate defaults to the one set in your OS for your playback device

		*/
		
		audioContext = new AudioContext({sampleRate: 16000});
		
		/*  assign to gumStream for later use  */
		gumStream = stream;
		
		/* use the stream */
		input = audioContext.createMediaStreamSource(stream);

		/* 
			Create the Recorder object and configure to record mono sound (1 channel)
			Recording 2 channels  will double the file size
		*/
		rec = new Recorder(input,{numChannels:1})

		//start the recording process
		rec.record()

		console.log("Recording started");
		
		

	}).catch(function(err) {
	  	//enable the record button if getUserMedia() fails
    	//recordButton.disabled = false;
    	//stopButton.disabled = true;
    	//pauseButton.disabled = true
	});
}

function pauseRecording(){
	console.log("pauseButton clicked rec.recording=",rec.recording );
	if (rec.recording){
		//pause
		rec.stop();
		pauseButton.innerHTML="Resume";
	}else{
		//resume
		rec.record()
		pauseButton.innerHTML="Pause";

	}
}

function stopRecording() {
	console.log("stopButton clicked");
	$("#stopButton").removeClass("button-animate");
	//disable the stop button, enable the record too allow for new recordings
	stopButton.disabled = true;
	recordButton.disabled = false;
	pauseButton.disabled = true;

	document.getElementById("stopButton").style.display = "none";
  	document.getElementById("recordButton").style.display = "inline-block";
	//reset button just in case the recording is stopped while paused
	pauseButton.innerHTML="Pause";
	
	//tell the recorder to stop the recording
	rec.stop();

	//stop microphone access
	gumStream.getAudioTracks()[0].stop();

	//create the wav blob and pass it on to createDownloadLink
	rec.exportWAV(createDownloadLink);
	
	
}

function createDownloadLink(blob) {
	$("#recordingsList").empty();
	var url = URL.createObjectURL(blob);
	var au = document.createElement('audio');
	var li = document.createElement('li');
	
	
	
	const button1 = document.createElement('button');
	button1.classList.add('btn', 'btn-primary');
	button1.style.marginBottom = '40px';
	button1.style.marginRight = '25px';
	var link = document.createElement('a');

	//name of .wav file to use during upload and download (without extendion)
	var filename = new Date().toISOString();

	//add controls to the <audio> element
	au.controls = true;
	au.src = url;
	
	 let text = document.getElementById("text_id");
  	let originalText = text.innerText;
	au.addEventListener("canplaythrough", () => {
		au.play();
		
		 au.addEventListener("timeupdate", (event) => {
    	  const currentTime = au.currentTime;
    	  const spokenWords = calculateSpokenWords(originalText);
  
    	  let currentWord = null;
          for (let i = 0; i < spokenWords.length; i++) {
            if (currentTime >= spokenWords[i].startTime && currentTime <= spokenWords[i].endTime) {
              currentWord = spokenWords[i].word;
              break;
            }
          }
          
          if (currentWord) {
            const charIndex = originalText.indexOf(currentWord);
            const charLength = currentWord.length;
            text.innerHTML = highlight(originalText, charIndex, charIndex + charLength);
          }
        });
	});
	//save to disk link
	link.href = url;
	link.download = filename+".wav"; //download forces the browser to donwload the file using the  filename
	button1.setAttribute('id', link); 
	button1.textContent = 'Save to disk';
	link.appendChild(button1);
	//add the new audio element to li
	li.appendChild(au);
	
	//add the filename to the li
	li.appendChild(document.createTextNode(filename+".wav "))

	//add the save to disk link to li
	li.appendChild(link);
	
	
	//upload link
	const button = document.createElement('button');
	button.classList.add('btn', 'btn-success');
	button.style.marginBottom = '40px';
	var upload = document.createElement('a');
	upload.href="#";
	upload.addEventListener("click", function(event){
		 $("#overlay").fadeIn();　
		  var xhr=new XMLHttpRequest();
		  xhr.onload=function(e) {
		      if(this.readyState === 4) {
				var response = JSON.parse(this.responseText);
		          console.log(response);
		          
		          	$('#rateit-demo').rateit({ 
							min: 0, max: 5, step: 0.5, mode: 'bg', 
							icon: '★', starwidth: 16, starheight: 16, 
							readonly: false, resetable: true, ispreset: false
	  					});
	 				$('#rateit-demo').rateit('value', response.result);
	 				
	 				$("#overlay").fadeOut();
	 				
	 				
	 				 var jsonData = response.output;
	 				
 var pronunciationChartCtx = document.getElementById('pronunciationChart').getContext('2d');
  var fluencyRhythmChartCtx = document.getElementById('fluencyRhythmChart').getContext('2d');
  
  // Process jsonData and extract necessary data for charts
  
  // Example data for demonstration
  var words = jsonData.words;
	var pronunciationScores = words.map(function(word) {
	return word.scores.pronunciation;
	});
  var fluency = jsonData.fluency;
//var rhythm = jsonData.rhythm;
var overall = jsonData.overall;
var pronunciation=jsonData.pronunciation;

var fluencyRhythmData = [overall, fluency, pronunciation ];
  
  // Create charts
  new Chart(pronunciationChartCtx, {
    type: 'bar',
   data: {
      labels: jsonData.words.map(function(word) {
        return word.word;
      }),
      datasets: [{
        label: 'Pronunciation Score',
        data: pronunciationScores,
        backgroundColor: 'rgba(255, 99, 132, 0.5)',
        borderColor: 'rgba(255, 99, 132, 1)',
        borderWidth: 1
      }]
    },
    options: {
      scales: {
        y: {
          beginAtZero: true,
          max: 100
        }
      }
    }
  });
  
  new Chart(fluencyRhythmChartCtx, {
    type: 'polarArea',
    data: {
      labels: ['Overall', 'Fluency', 'Pronunciation' ],
      datasets: [{
        data: fluencyRhythmData,
        backgroundColor: ['rgb(51, 153, 51)', 'rgba(75, 192, 192, 0.5)', 'rgba(255, 99, 132, 0.5)']
      }]
    }
  });
  
  var words = jsonData.words;

  // Create Word Pronunciation Charts
  var wordChartsDiv = document.getElementById("wordCharts");
  jsonData.words.forEach(function(word) {
    var wordChartCanvas = document.createElement("canvas");
    wordChartCanvas.className = "my-4";
	wordChartCanvas.setAttribute("width", "546"); // Set canvas width
    wordChartCanvas.setAttribute("height", "273"); // Set canvas height
    wordChartsDiv.appendChild(wordChartCanvas);
    
	var phonemeLabels = word.phonics.map(function(phoneme) {
      return phoneme.spell;
    });

    var phonemeScores = word.phonics.map(function(phoneme) {
      return phoneme.overall;
    });
    
	var wordChartCtx = wordChartCanvas.getContext("2d");
    var wordChart = new Chart(wordChartCtx, {
      type: 'bar',
      data: {
        labels: phonemeLabels,
        datasets: [{
          label: 'Phoneme Score',
          data: phonemeScores,
          backgroundColor: 'rgba(75, 192, 192, 0.2)',
          borderColor: 'rgba(75, 192, 192, 1)',
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  });
	 				
	 			$("#infoIcon").css("display", "contents");
	 				
		      }
		  };
		  var fd=new FormData();
		 // alert($("#text_id").val());
		  fd.append("text", $("#text_id").text());
		  fd.append("file",blob, filename);
		 // xhr.open("POST", url+"/record/submit_audio");
		  xhr.open("POST", "https://dev.zevoirtechnologies.com:8443/pragna_dev/record/submit_audio");
		  xhr.send(fd);
	})
	button.textContent = 'Upload';
	upload.appendChild(button);
	li.appendChild(document.createTextNode (" "))//add a space in between
	li.appendChild(upload)//add the upload link to li

	var divElement = document.createElement("div");
	divElement.className = "rateit";
	divElement.id = "rateit-demo";
	divElement.setAttribute("data-rateit-mode", "font");
	divElement.style.fontSize = "50px";
	divElement.style.marginLeft = '25px';
	
	li.appendChild(divElement);
	
	var info = document.createElement("div");
	info.className = "info-icon";
	info.id = "infoIcon";
	info.innerHTML = '<i class="fa fa-info-circle"></i>';
	info.style.display="none";
	
	li.appendChild(info);
	//add the li element to the ol
	recordingsList.appendChild(li);
	
	//Get the modal
var modal = document.getElementById("myModal");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the icon, open the modal
document.getElementById("infoIcon").addEventListener("click", function() {
  modal.style.display = "block";
});

// When the user clicks on <span> (x), close the modal
span.addEventListener("click", function() {
  modal.style.display = "none";
});

// When the user clicks anywhere outside of the modal, close it
window.addEventListener("click", function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
});
}


const highlight1 = (text, from, to) => {
	console.log(text);
  let replacement = highlightBackground1(text.slice(from, to));
  console.log(replacement);
  return text.substring(0, from) + replacement + text.substring(to);
};
const highlightBackground1 = (sample) =>
  `<span  style="background-color:blue;">${sample}</span>`;
