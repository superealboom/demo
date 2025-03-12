$(function() {
    $("#captchaId").val(captchaId);
})

/**
 * 刷新验证码
 */
function refreshCaptcha() {
    $.ajax({
        url: PATH + '/captcha/getCaptcha',
        contentType: 'application/x-www-form-urlencoded',
        method: 'get',
        data: {},
        dataType:"json",
        success: function (response) {
            $('#captchaImage').attr('src',  response.data.captchaImage);
            $("#captchaId").val(response.data.captchaId);
        }
    })
}

/**
 * 验证验证码
 */
function checkCaptcha() {
    const formData = {};
    formData['captchaId'] = $("#captchaId").val();
    formData['captchaCode'] = $("#captchaCode").val();
    $.ajax({
        url: PATH + '/captcha/checkCaptcha',
        method: 'post',
        data: formData,
        dataType:"json",
        success: function (response) {
            showAlert(response.message);
        }
    })
}

function showAlert(message) {
    const modalBody = document.getElementById('messageModalBody');
    modalBody.textContent = message;
    const modal = new bootstrap.Modal(document.getElementById('messageModal'));
    modal.show();
}