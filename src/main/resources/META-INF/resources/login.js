const URL = 'http://localhost:8080';
let entries = [];

const login = (e) => {
    e.preventDefault();
    const loginData = new FormData(e.target);

    const login = {};

    login['username'] = loginData.get('username');
    login['password'] = loginData.get('password');


    fetch(`${URL}/auth/loginn`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'

        },
        body: JSON.stringify(login)
    }).then((result) => {
        console.log(result);
    })

}


document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#loginForm');
    createEntryForm.addEventListener('submit', login);
});