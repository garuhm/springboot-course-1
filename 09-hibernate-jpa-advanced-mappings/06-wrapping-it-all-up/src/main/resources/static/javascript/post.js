const showPostEditFormBtn = document.querySelector(".show-post-edit-form")
const deletePostBtn = document.querySelector(".show-delete-confirmation")

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