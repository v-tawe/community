$(function() {
    editormd.markdownToHTML("question-desc",{});
});

function submitComment() {
    var quesitonId = $('#question_id').val();
    var content = $('#comment_content').val();
    // console.log(quesitonId + content);
    $.ajax({
            type: "post",
            url: "/comment",
            contentType: "application/json",
            data: JSON.stringify({
                "parentId": quesitonId,
                "content": content,
                "type": 1
            }),
            dataType: "json",
            success: function (response) {
                if (response.code == 200) {
                    window.location.reload();
                    $('#comment_section').hide();
                } else if (response.code == 1001) {
                    let isAccepted = confirm(response.message);
                    if (isAccepted) {
                        localStorage.closable = true;
                        window.open("https://github.com/login/oauth/authorize?client_id=eff06f54074ea9e4eb04&redirect_url=http://localhost:8887/callback&scope=user&state=1");
                    }
                }
                console.log(response);
            },
            error: function (response) {
                console.log(response);
            }
        }
    )
}
