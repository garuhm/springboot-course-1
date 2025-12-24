const showPostEditFormBtn = document.querySelector(".show-post-edit-form")
const showDeletePostBtn = document.querySelector(".show-delete-confirmation")

showPostEditFormBtn.addEventListener("click", (e) => {
    e.preventDefault()

    const editPostForm = document.querySelector(".edit-post")
    if(editPostForm.classList.contains("hidden")) {
        editPostForm.classList.remove("hidden")
        deletePostBtn.classList.add("hidden")
    } else {
        editPostForm.classList.add("hidden")
        deletePostBtn.classList.remove("hidden")
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

const noBtn = document.querySelector(".no")
noBtn.addEventListener("click", (e) => {
    e.preventDefault()

    const deletePostForm = document.querySelector(".delete-post")
    deletePostForm.classList.add("hidden")
    showPostEditFormBtn.classList.remove("hidden")
})