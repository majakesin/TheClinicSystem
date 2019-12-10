var id_records="";
$(document).ready(function() {
	
	
	$("#changePacient").on('change', function() {
		pacientId = this.value;
		$.ajax({
		type : "GET",
		url : "/doctors/records/" + pacientId,
		contentType : "application/json/",
			success : function(data) {
				tableRender(data);
				id_records=data.idDto;
			}

		});

	});
	
	$("#saveChanges").click(function(){
		
		alert("egage");
		
	});
	
	
	

	function tableRender(data) {
		$('#pacientRecords').empty();

		$("#pacientRecords")
							.append(
										"<div my-5><tr><td><label>Height</label></td>"
												+ "<td><input id=\"pacientHeight\" class=\"form-control ml-3\" placeholder=\"Height\" value="
												+ data.heightDto
												+ "></tr></div>");

						$("#pacientRecords")
								.append(
										"<div><tr><td><label>Weight</label></td>"
												+ "<td><input id=\"pacientWeight\" class=\"form-control ml-3\" placeholder=\"Weight\" value="
												+ data.weightDto
												+ "></tr></div>");

						$("#pacientRecords")
								.append(
										"<div><tr><td><label>Blood type</label></td>"
												+ "<td><input id=\"blood\" class=\"form-control ml-3\" placeholder=\"Blood type\" value="
												+ data.bloodType
												+ "></tr></div>");

						$("#pacientRecords")
								.append(
										"<div><tr><td><label>Allergy</label></td>"
												+ "<td><input id=\"allergy\" class=\"form-control ml-3\" placeholder=\"Allergy\" value="
												+ data.allergy
												+ "></tr></div>");
						$("#pacientRecords")
						.append(
								"<div><tr><td></td><td></td></tr><tr><td><input type=\"submit\" id=\"saveChanges\" class=\"btn btn-outline-danger\" value=\"Sacuvaj izmene\" onclick=\"foo();\" /></td></tr>" +
								"</table></div>");
						
	}
					
	
					

});

function foo() {
	var e = document.getElementById("changePacient");
	var id_pacient=e.options[e.selectedIndex].value;
	var pacientHeight=$("#pacientHeight").val();
	var pacientWeight=$("#pacientWeight").val();
	var blood=$("#blood").val();
	var allergy=$("#allergy").val();
	
	
	
	$.ajax({
		type : "POST",
		url : "/doctors/records/edit",  
		contentType : "application/json",
		data : JSON.stringify({
			"idDto" : id_records,
			"weightDto" : pacientWeight,
			"heightDto" : pacientHeight,
			"bloodType":blood,
			"allergy" : allergy
		}),
		
		success : function(result) {
			location.reload();
		}
	});
		
}