const showPostFormBtn = document.querySelector(".show-post-form")
showPostFormBtn.addEventListener("click", (e) => {
    e.preventDefault()

    const addPostForm = document.querySelector(".add-post")
    if(addPostForm.classList.contains("hidden")) {
        addPostForm.classList.remove("hidden")
    } else {
        addPostForm.classList.add("hidden")
    }
})