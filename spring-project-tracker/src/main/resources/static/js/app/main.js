var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-edit').on('click', function() {
            _this.edit();
        })
        $('#btn-delete').on('click', function() {
            _this.delete();
        })
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };
        $.ajax({
            type: 'POST',
            url: '/posts',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
            error:function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        }).done(function() {
            location.reload();
        }).fail(function (error) {
            alert(error);
        });
    },
    edit : function() {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };
        $.ajax({
            type: 'PUT',
            url: '/posts/' + $('#id').val(),
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
            error:function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        }).done(function() {
            location.reload();
        }).fail(function (error) {
            alert(error);
        });
    },
    delete : function() {
        $.ajax({
            type: 'DELETE',
            url: '/posts/' + $('#id').val(),
            contentType:'application/json; charset=utf-8',
            error:function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        }).done(function() {
            location.href = "/";
        }).fail(function (error) {
            alert(error);
        });
    }
};

main.init();