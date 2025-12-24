const showPostEditFormBtn = document.querySelector(".show-post-edit-form")
const showDeletePostBtn = document.querySelector(".show-post-delete-confirmation")

showPostEditFormBtn.addEventListener("click", (e) => {
    e.preventDefault()

    const editPostForm = document.querySelector(".edit-post")
    if(editPostForm.classList.contains("hidden")) {
        editPostForm.classList.remove("hidden")
        showDeletePostBtn.classList.add("hidden")
    } else {
        editPostForm.classList.add("hidden")
        showDeletePostBtn.classList.remove("hidden")
    }
})

showDeletePostBtn.addEventListener("click", (e) => {
    e.preventDefault()

    const deletePostForm = document.querySelector(".delete-post")
    if(deletePostForm.classList.contains("hidden")) {
        deletePostForm.classList.remove("hidden")
        showPostEditFormBtn.classList.add("hidden")
    } else {
        deletePostForm.classList.add("hidden")
        showPostEditFormBtn.classList.remove("hidden")
    }
})

const noBtnPost = document.querySelector(".delete-post > .no")
noBtnPost.addEventListener("click", (e) => {
    e.preventDefault()

    const deletePostForm = document.querySelector(".delete-post")
    deletePostForm.classList.add("hidden")
    showPostEditFormBtn.classList.remove("hidden")
})

const showCommentEditFormBtn = document.querySelector(".show-comment-edit-form")
const showDeleteCommentBtn = document.querySelector(".show-comment-delete-confirmation")

showCommentEditFormBtn.addEventListener("click", (e) => {
    e.preventDefault()

    const editCommentForm = document.querySelector(".edit-comment")
    if(editCommentForm.classList.contains("hidden")) {
        editCommentForm.classList.remove("hidden")
        showDeleteCommentBtn.classList.add("hidden")
    } else {
        editCommentForm.classList.add("hidden")
        showDeleteCommentBtn.classList.remove("hidden")
    }
})

showDeleteCommentBtn.addEventListener("click", (e) => {
    e.preventDefault()

    const deleteCommentForm = document.querySelector(".delete-comment")
    if(deleteCommentForm.classList.contains("hidden")) {
        deleteCommentForm.classList.remove("hidden")
        showCommentEditFormBtn.classList.add("hidden")
    } else {
        deleteCommentForm.classList.add("hidden")
        showCommentEditFormBtn.classList.remove("hidden")
    }
})

const noBtnComment = document.querySelector(".delete-comment > .no")
noBtnComment.addEventListener("click", (e) => {
    e.preventDefault()

    const deleteCommentForm = document.querySelector(".delete-comment")
    deleteCommentForm.classList.add("hidden")
    showCommentEditFormBtn.classList.remove("hidden")
})