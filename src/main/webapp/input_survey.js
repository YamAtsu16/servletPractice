var elmSubmit = document.getElementById("ID_SUBMIT");
elmSubmit.onclick = function(){
  var elmAge     = document.getElementById("ID_AGE");
  var elmMessage = document.getElementById("ID_MESSAGE");
  var canSubmit  = true;
  if(elmAge.value == ""  || !(elmAge.value.match("^[0-9]+$")) || elmMessage.value == "" ){
    alert("不正な入力項目があります。");
    canSubmit = false;
  }
  return canSubmit;
}
