function eliminar(id){

  console.log("eliminar"+id)

    swal({
        title: "¿Estás seguro de eliminar?",
        text: "Una vez eliminado no podrás recuperar el registro!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
      .then((OK) => {
        if (OK) {     
            $.ajax({
              type: "get",
              url: "/libros/delete/"+id,
              data: "data",
              dataType: "dataType",
              success: function (response) {
                  console.log(response);
              }
            });

            swal("Poof! El registro ha sido eliminado satisfactoriamente", {
              icon: "success",
            }).then((ok)=>{
                location.href="/libros/all"
            });

        } else {
          swal("Cancelado!");
        }
      });

}