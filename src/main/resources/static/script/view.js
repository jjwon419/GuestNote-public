let allNotes = [];
let newNotes = [];
const name = document.getElementById('name');
const content = document.getElementById('content');

function sleep(ms) {
    const wakeUpTime = Date.now() + ms;
    while (Date.now() < wakeUpTime) {
    }
}

function hide() {
    $('#name').fadeOut(500);
    $('#content').fadeOut(500);
}

function show() {
    $('#name').fadeIn(500);
    $('#content').fadeIn(500);
}

async function getAllNotes() {
    const res = await fetch('/note/all');
    allNotes = await res.json();
}

function startView() {
    let index = 0;
    let maxIndex = allNotes.length - 1;
    function showNextNote(){
            hide();
            setTimeout(() => {
                if(newNotes.length > 0){
                    const newNote = newNotes.shift();
                    name.textContent = newNote.name;
                    content.textContent = newNote.content;

                    allNotes.push(newNote);
                }else{
                    name.textContent = allNotes[index].name;
                    content.textContent = allNotes[index].content;

                    if(index < maxIndex){
                        index++;
                    }else{
                        index = 0;
                        maxIndex = allNotes.length - 1;
                    }
                }

                show();

            }, 500);
            setTimeout(showNextNote, 7000);
    }

    showNextNote();
}

getAllNotes().then(() => {
    startView();
});

const socket = new WebSocket("ws://jjwon419.kr/note/new");
socket.onmessage = (event) => {
    console.log("new note");
    newNotes.push(JSON.parse(event.data));
};