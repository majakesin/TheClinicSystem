$(document)
		.ready(
				function() {
					$("#changePacient").on('change', function() {
						pacientId = this.value;
						$.ajax({
							type : "GET",
							url : "/doctors/records/" + pacientId,
							contentType : "application/json/",
							success : function(data) {
								tableRender(data);
							}

						});

					});

					function tableRender(data) {
						$('#pacientRecords').empty();

						$("#pacientRecords")
								.append(
										"<div my-5><tr><td><label>Height</label></td>"
												+ "<td><input class=\"form-control ml-3\" placeholder=\"Height\" value="
												+ data.heightDto
												+ "></tr></div>");

						$("#pacientRecords")
								.append(
										"<div><tr><td><label>Weight</label></td>"
												+ "<td><input class=\"form-control ml-3\" placeholder=\"Weight\" value="
												+ data.weightDto
												+ "></tr></div>");

						$("#pacientRecords")
								.append(
										"<div><tr><td><label>Blood type</label></td>"
												+ "<td><input class=\"form-control ml-3\" placeholder=\"Blood type\" value="
												+ data.bloodTypeDto
												+ "></tr></div>");

						$("#pacientRecords")
								.append(
										"<div><tr><td><label>Allergy</label></td>"
												+ "<td><input class=\"form-control ml-3\" placeholder=\"Allergy\" value="
												+ data.allergyDto
												+ "></tr></div>");
						$("#pacientRecords")
						.append(
								"<div><tr><td></td><td></td><td>" +
								"</tr></tr><td></table>");
						
					}

				});