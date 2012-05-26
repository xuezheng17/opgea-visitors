function setupCamera(){
    
    webcam.set_api_url('visitors/createImage');
    webcam.set_swf_url('../resources/jquery/webcam.swf');
    webcam.set_quality(90);
    webcam.set_shutter_sound(true, '../resources/jquery/shutter.mp3');
    document.getElementById('camera').innerHTML = webcam.get_html(230, 150);
    webcam.set_stealth(true);
    
}
