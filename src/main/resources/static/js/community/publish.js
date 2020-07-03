$(function() {
    editormd("description-desc", {
        width: "100%",
        height: "400px",
        path: "/js/lib/",
        watch: false,
        imageUpload : true,
        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL : "/file/upload"
    })
});