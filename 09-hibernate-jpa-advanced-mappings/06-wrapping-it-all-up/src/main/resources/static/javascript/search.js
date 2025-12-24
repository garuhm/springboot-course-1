const postsToggle = document.querySelector(".posts-toggle")
const usersToggle = document.querySelector(".users-toggle")
const posts = document.querySelector(".posts")
const users = document.querySelector(".users")

postsToggle.addEventListener("click", (e) => {
    posts.classList.remove("hidden")
    users.classList.add("hidden")
    usersToggle.classList.remove("active")
    postsToggle.classList.add("active")
})

usersToggle.addEventListener("click", (e) => {
    posts.classList.add("hidden")
    users.classList.remove("hidden")
    usersToggle.classList.add("active")
    postsToggle.classList.remove("active")
})