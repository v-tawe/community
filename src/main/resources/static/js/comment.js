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
                    $('#comment_section').hide();
                } else {
                    alert(response.message);
                }
                console.log(response);
            },
            error: function (response) {
                console.log(response);
            }
        }
    )
}