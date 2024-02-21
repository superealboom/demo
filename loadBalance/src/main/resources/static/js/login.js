function login() {
    var username = $("input[name='username']").val()
    var password = $("input[name='password']").val();
    var mode = $('input:radio:checked').val();
    if (mode === 'cookie') {
        $.ajax({
            type: "post",
            url:  "/cookie/login",
            data: {
                "username": username,
                "password": password
            },
            success: function(r) {
                if (r.code === 200) {
                    location.href = '/cookie/index';
                } else {
                    alert(r.message);
                }
            }
        });
    } else if (mode === 'session') {
        $.ajax({
            type: "post",
            url:  "/session/login",
            data: {
                "username": username,
                "password": password
            },
            success: function(r) {
                if (r.code === 200) {
                    location.href = '/session/index';
                } else {
                    alert(r.message);
                }
            }
        });
    }

}