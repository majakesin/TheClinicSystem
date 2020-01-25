$(document).ready(function(){
	
	$('#otvoriModalOdbij').click(posaljiOdbijenicu());
	


});

function posaljiOdbijenicu() {
	
	 return function(event){
		 
		 $('#OdbijenicaModalCenter').modal('show');
		 
	 }
	
}