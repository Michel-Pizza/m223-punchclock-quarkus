const URL = 'http://localhost:8080';
let entries = [];

const dateAndTimeToDate = (dateString, timeString) => {
    return new Date(`${dateString}T${timeString}`).toISOString();
};

const createEntry = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    const category = {};
    entry['checkIn'] = dateAndTimeToDate(formData.get('checkInDate'), formData.get('checkInTime'));
    entry['checkOut'] = dateAndTimeToDate(formData.get('checkOutDate'), formData.get('checkOutTime'));
    category['id'] = formData.get('id');
    entry['category'] = category;

    if(entry['checkIn'] < entry['checkOut']){
        fetch(`${URL}/entries`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'bearer '
            },
            body: JSON.stringify(entry)
        }).then((result) => {
            result.json().then((entry) => {
                entries.push(entry);
                renderEntries();
            });
        });
    } else{
        alert("Error: end is less than start")

    }


};

const indexEntries = () => {
    fetch(`${URL}/entries`, {
        method: 'GET'
    }).then((result) => {
        result.json().then((result) => {
            entries = result;
            renderEntries();
        });
    });
    renderEntries();
};


const  deleteEntry = async (id) => {
    fetch(`${URL}/entries/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
        }).then( () => {
            renderEntries();

    });
await renderEntries();
}

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

const createButton = (id) => {
    const cell1 = document.createElement('td');
    const cell = document.createElement('button');
    cell.innerText = "delete";
    cell.onclick = function () {
        deleteEntry(id)
    }
    cell1.appendChild(cell)
    return cell1;
}



const renderEntries = () => {
    const display = document.querySelector('#entryDisplay');
    display.innerHTML = '';
    entries.forEach((entry) => {
        const row = document.createElement('tr');
        row.appendChild(createCell(entry.id));
        row.appendChild(createCell(new Date(entry.checkIn).toLocaleString()));
        row.appendChild(createCell(new Date(entry.checkOut).toLocaleString()));
        row.appendChild(createCell(entry.category.title))
        row.appendChild(createButton(entry.id));
        display.appendChild(row);
    });
};

document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#createEntryForm');
    createEntryForm.addEventListener('submit', createEntry);
    indexEntries();
});