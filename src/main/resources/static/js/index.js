const searchGiphy = async () => {
    var search = document.getElementById('matterbot-text').value;
    const response = await fetch('/call/giphy/search2?query=' + search);
    const json = await response.json();
    document.getElementById('output').innerHTML = "";
    json.forEach(preview);
};

const postAscii = async () => {
    document.getElementById('output').innerHTML = "";
    var search = document.getElementById('matterbot-text').value;
    document.getElementById('matterbot-text').value = "";
    await fetch('/call/ascii/search?query=' + search);
};

function postImageToMattermost(index) {
    let title = document.getElementById('matterbot-text').value;
    let uri = document.getElementById('image_' + index).getAttribute('src');

    const url = '/call/giphy/img?url=' + encodeURI(uri) + '&title=' + title;

    const Http = new XMLHttpRequest();
    Http.open("GET", url);
    Http.send();

    Http.onreadystatechange = () => {
        console.log(Http.responseText)
    };
}

function searchUrbanText() {
    let text = document.getElementById('matterbot-text').value;
    const url = '/call/urbandict/search?query=' + encodeURI(text);

    const Http = new XMLHttpRequest();
    Http.open("GET", url);
    Http.send();

    Http.onreadystatechange = () => {
        console.log(Http.responseText)
    };
}

function postWeather() {
    let location = document.getElementById('matterbot-text').value;
    const url = '/call/openweather/location?query=' + encodeURI(location);

    const Http = new XMLHttpRequest();
    Http.open("GET", url);
    Http.send();

    Http.onreadystatechange = () => {
        console.log(Http.responseText)
    };
}


document.getElementById('giphy-button').addEventListener('click', searchGiphy);
document.getElementById('ascii-button').addEventListener('click', postAscii);

document.getElementById('urban-button').addEventListener('click', searchUrbanText);
document.getElementById('weather-button').addEventListener('click', postWeather);



function preview(element, index) {
    let width = window.innerWidth / 5.1;
    document.getElementById('output').innerHTML +=
        `<img id='image_${index}' src='${element}' alt="gif" width="${width}" onclick="postImageToMattermost(${index});">`;
}

