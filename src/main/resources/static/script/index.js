function onClickSubmitButton(){
    console.log("on click button");
    let value = document.cookie.match('(^|;) ?isSubmit=([^;]*)(;|$)');
    value = value ? value[2] : null;

    if(value === 'true'){
        Swal.fire({
            title: "이미 작성하셨습니다ㅠㅠ",
            text: "24시간 후에 시도해주세요.",
            icon: "error"
        });
        return;
    }
    fetch("/note/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: document.getElementById('name').value,
            content: document.getElementById('content').value
        })
    }).then(() => {
        console.log("complete to submit");
        var today = new Date();
        today.setHours(24);
        today.setMinutes(0);
        today.setSeconds(0);
        document.cookie = "isSubmit=true; expires=" + today.toUTCString();
        Swal.fire({
            title: "방명록 작성 완료!",
            icon: "success"
        })
    });
}