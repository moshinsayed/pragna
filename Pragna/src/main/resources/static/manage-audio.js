// audio recorder
let recorder, audio_stream;
let mediaBlobArray=[];
const recordButton = document.getElementById("recordButton");
recordButton.addEventListener("click", startRecording);

// stop recording
const stopButton = document.getElementById("stopButton");
stopButton.addEventListener("click", stopRecording);
stopButton.disabled = true;

// set preview
const preview = document.getElementById("audio-playback");

// set download button event
const downloadAudio = document.getElementById("downloadButton");
downloadAudio.addEventListener("click", downloadRecording);

function startRecording() {
    // button settings
    recordButton.disabled = true;
	 $("#recordButton").hide();
	 $("#stopButton").show();
   // recordButton.innerText = "Recording..."
    $("#stopButton").addClass("button-animate");

    $("#stopButton").removeClass("inactive");
    stopButton.disabled = false;


    if (!$("#audio-playback").hasClass("hidden")) {
        $("#audio-playback").addClass("hidden")
    };

    if (!$("#downloadContainer").hasClass("hidden")) {
        $("#downloadContainer").addClass("hidden")
    };

    navigator.mediaDevices.getUserMedia({ audio: true })
        .then(function (stream) {
            audio_stream = stream;
            recorder = new MediaRecorder(stream);

            // when there is data, compile into object for preview src
            recorder.ondataavailable = function (e) {
				mediaBlobArray.push(e.data);
                const url = URL.createObjectURL(e.data);
                preview.src = url;

                // set link href as blob url, replaced instantly if re-recorded
                downloadAudio.href = url;
            };
            
            recorder.onstop = () => {
        		const opusData = new Blob(mediaBlobArray, { type: 'audio/ogg; codecs=opus' });
        		convertToPCM(opusData);
      		};
      		
            recorder.start();

            timeout_status = setTimeout(function () {
                console.log("5 min timeout");
                stopRecording();
            }, 300000);
        });
}

function stopRecording() {
    recorder.stop();
    audio_stream.getAudioTracks()[0].stop();

    // buttons reset
    recordButton.disabled = false;
    //recordButton.innerText = "Redo Recording"
    $("#stopButton").removeClass("button-animate");

    $("#stopButton").addClass("inactive");
    stopButton.disabled = true;
	$("#stopButton").hide();
	$("#recordButton").show();

    $("#audio-playback").removeClass("hidden");

    $("#downloadContainer").removeClass("hidden");
}

function downloadRecording(){
    var name = new Date();
    var res = name.toISOString().slice(0,10);
    var fileName=res + '.wav';
    downloadAudio.download = fileName;
    
   const opusData = new Blob(chunks, { type: 'audio/ogg; codecs=opus' });
    convertToPCM(opusData);
    
    var formData = new FormData();
	   formData.append("file", blob, fileName);
	   
	   var xhr = new XMLHttpRequest();
	   xhr.open("POST", "/record/submit_audio");
	   xhr.send(formData);
	   xhr.addEventListener("readystatechange", function () {
	   	  if (this.readyState === 4) {
	   		 console.log(this.responseText);
	   		}
	   	});
}


function convertToPCM(opusData) {
	console.log(opusData);
  var audioContext = new (window.AudioContext || window.webkitAudioContext)();
  console.log(audioContext);
  audioContext.decodeAudioData(opusData, (buffer) => {
    // The buffer contains the PCM audio data in the desired format.
    // You can now process the PCM data as needed.
    console.log('PCM audio data:', buffer);
  });
}
