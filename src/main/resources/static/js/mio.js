$("#fileUpload").on('change', function () {

     //Get count of selected files
     var countFiles = $(this)[0].files.length;

     var imgPath = $(this)[0].value;
     var extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
     var image_holder = $("#image-holder");
     image_holder.empty();

     if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg"|| extn == "pdf") {
         if (typeof (FileReader) != "undefined") {

             //loop for each file selected for uploaded.
             for (var i = 0; i < countFiles; i++) {

                 var reader = new FileReader();
                 reader.onload = function (e) {
                     $("<img />", {
                         "src": e.target.result,
                             "class": "img-thumbnail","id": "foto"
                     }).appendTo(image_holder);
                 }
                 image_holder.show();
                 reader.readAsDataURL($(this)[0].files[i]);
                 document.getElementById("btncerrar").className = "btn btn-xs btn-danger";
                 document.getElementById("textcredencial").innerHTML = "Credencial Agregada";
                 document.getElementById("textcredencial").className = "bg-success custom-file-label text-left text-light";
                 
             }

         } else {
             alert("Esta Navegador no soporta la lectura de archivos.");
         }
     } else {
         alert("Porfavor selecciona Imagenes o PDF");
     }
 });

function something_happens() {
	document.getElementById("foto").src = null;
	document.getElementById("fileUpload").value = "";
	document.getElementById("btncerrar").className = "invisible";
	document.getElementById("textcredencial").className = "custom-file-label text-left";
	   var div = document.getElementById('foto');
	        var parent = div.parentElement;
	        parent.removeChild(div);
	        document.getElementById("textcredencial").innerHTML = "Agregar Credencial";
};