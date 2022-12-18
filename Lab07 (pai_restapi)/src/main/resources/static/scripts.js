let apiUrl = 'http://localhost:8080'

// localhost:8080/students
function showAllStudents() {
    let table = '<table> ' +
        '<thead> ' +
            '<tr> ' +
                '<th> ID </th> ' +
                '<th> Name </th> ' +
                '<th> Surname </th> ' +
                '<th> Average </th> ' +
                '<th> Edycja </th>' +
                '<th> Usuwanie </th>' +
            '</tr> ' +
        '</thead> <tbody>'

    fetch(apiUrl + '/students')
        .then((response) => response.json())
        .then((data) => {
                data.forEach(element => {
                    let id = element['id'];
                    table += '<tr> ' +
                        '<td>'+ element['id'] +'</td> ' +
                        '<td>'+ element['name'] +'</td> ' +
                        '<td>'+ element['surname'] +'</td> ' +
                        '<td>'+ element['average']+'</td> ' +
                        '<td> <button type="button" onclick="showAddEditForm(\'POST\', \'EDIT\',' + id +')"> Edytuj </button> </td> ' +
                        '<td> <button type="button" onclick="deleteStudent(' + id + ')"> Usuń </button> </td>' +
                        '</tr>';
                })
                table += '</tbody> </table>';
                document.getElementById('content').innerHTML = table;
            }
        );
}

// localhost:8080/edit/{id}
function editStudent(id){
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    let data = {}
    data.name = document.getElementById('name').value;
    data.surname = document.getElementById('surname').value;
    data.average = parseFloat(document.getElementById('average').value);

    let requestOptions = {
        method: 'PUT',
        headers: myHeaders,
        body: JSON.stringify(data),
        redirect: 'follow'
    };
    let url = apiUrl + "/edit/" + id;
    fetch(url, requestOptions)
        .then(response => response.text())
        .then(result => {
            window.alert("Zaktualizowano dane studenta!");
            showAllStudents();
        })
        .catch(error => window.alert(error));
}

// localhost:8080/delete/{id}
function deleteStudent(id){
    let requestOptions = {
        method: 'DELETE',
        redirect: 'follow'
    };

    let url = apiUrl + "/delete/" + id;
    fetch(url , requestOptions)
        .then((response) => response.text())
        .then((result) => {
            window.alert(result);
            showAllStudents();
        })
        .catch(error => window.alert(error));
}

// localhost:8080/add
function addStudent(){
    let data = {}
    if(document.getElementById('id').value !== ""){
        data.id = document.getElementById('id').value;
    }
    data.name = document.getElementById('name').value;
    data.surname = document.getElementById('surname').value;
    data.average = parseFloat(document.getElementById('average').value);

    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    let requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: JSON.stringify(data),
        redirect: 'follow'
    };

    let url = apiUrl + "/add";
    fetch(url, requestOptions)
        .then(response => response.text())
        .then(result => {
            window.alert(result)
            showAllStudents();
        })
        .catch(error => window.alert(error));

}

// Edit or add form
function showAddEditForm(method, mode, id){
    let form = '<form method="'+ method+'" action="localhost:8080/scripts.js">' +
        ' <input type="number" id="id" hidden> ' +
        '<table> ' +
        '<tr> <td> Name </td> <td> <input type="text" id="name"> </td> </tr>' +
        '<tr> <td> Surname </td> <td><input type="text" id="surname"> </td> </tr>' +
        '<tr> <td> Average </td> <td> <input type="number" min="0.00" max="5.00" step="0.01" id="average"> </td> </tr>';

    if(mode === "ADD")
        form += '<tr> <td colspan="2"> <button type="button" onClick="addStudent()"> Dodaj studenta </button> </td> </tr>';
    else
        form += '<tr> <td colspan="2"> <button type="button" onClick="editStudent('+ id +')"> Aktualizuj studenta </button> </td> </tr>';

    form +=  '</table> </form>';
    document.getElementById('content').innerHTML = form;

    if(mode === "EDIT"){
        let requestOptions = {
            method: 'GET',
            redirect: 'follow'
        };

        let url = apiUrl + "/student/" + id;
        fetch(url, requestOptions)
            .then(response => response.text())
            .then(result => {
                if(result !== null){
                    let data = JSON.parse(result);
                    document.getElementById('id').value = data['id'];
                    document.getElementById('name').value = data['name'];
                    document.getElementById('surname').value = data['surname'];
                    document.getElementById('average').value = data['average'];
                } else {
                    window.alert("Błąd pobierania danych studenta!");
                    showAllStudents();
                }
            })
            .catch(error => window.alert("Błąd edycji studenta!"));
    }
}
