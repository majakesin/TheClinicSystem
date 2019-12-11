$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#appointmentTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
  
  $("#myInput2").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#doctorsTable tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
  });
  
  $("#createOperation").on('click',function(){
	 alert("eag");
  });
  
  
  
  
});