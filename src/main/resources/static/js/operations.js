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
	 
  });
  
  $('#createOperation').click(function(){
	  var list = [];
	  
	  $(':checkbox:checked').each(function(i){
		  list[i] = $(this).val();
		  
      });
	  
	  $.ajax({
			type : "POST",
			url : "/operations/sendmail",
			dataType:"json",
			contentType: "application/json; charset=utf-8",
			data:JSON.stringify({"operationIds" :list}),
				success : function(data) {
					
				}
	  
	  });
	  alert(list);
  });
  
  
  
});