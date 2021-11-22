function loadResultGif() {
    $.ajax({
        url: 'http://localhost:8080/get',
        method: 'GET',
        dataType: "application/json",
        complete: function (data) {
        let out = document.querySelector("#out");
        if(data.status == 200) {
            let content = JSON.parse(data.responseText);
            let img = document.createElement("img");
            img.src = content.data.id;
            out.innerHTML = '';
            out.insertAdjacentElement("afterbegin", img);
        } else {
            var para = document.createElement("p");
            para.innerHTML = 'Something went wrong. Try again :)';
            out.insertAdjacentElement("afterbegin", para);
        }
        }
    })
}


           /*contentType: "application / json", // Тип запроса RequestBody
		           success: function(rs){
		        	   if(rs.status>0){
			        	  vm.datalist=rs.list;
			        	  if(type==1){
			        	  	generPageHtml(rs.currentPage,rs.totalPage,rs.total);
			        	  }
		        	   }else{

		        	   }
		           },
		           error: function(XMLHttpRequest, textStatus, errorThrown){
		           }*/