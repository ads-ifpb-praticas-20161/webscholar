function clearOtherRadioButtons(evnt)
{
    // Have to try 'currentTarget', then 'srcElement'.  Issue is Firefox
    // supports 'currentTarget' but IE does not.  IE supports 'srcElement'.
    var btn = (evnt.currentTarget) ? evnt.currentTarget : evnt.srcElement;
    var matchindexlocn = btn.id.indexOf('userselectedrecord');
    var btnidstarttxt = btn.id.substring(0,
        matchindexlocn + 18 ); // 'userselectedrecord' length = 18
    var elementid = '';
    var i = 0;
    var ele = null;
    var allElements = document.getElementsByTagName('*');

    for(i = 0; i < allElements.length; i++)
    {
        elementid = allElements[i].id;
        if ( (elementid.indexOf(btnidstarttxt) == -1) &&
            (elementid.indexOf('_') > -1) )
        {
            try
            {
                ele = document.getElementById(elementid);
                ele.checked = false;
            } catch (e) {}
        }
    }
}
function uncheckOthers(radio) {
    var name = radio.name.substring(radio.name.lastIndexOf(':'));
    var elements = radio.form.elements;
    for (var i = 0; i < elements.length; i++) {
        if (elements[i].name.substring(elements[i].name.lastIndexOf(':')) == name) {
            elements[i].checked = false;
        }
    }
    radio.checked = true;
}

var websocketSession = new WebSocket('ws://' + document.location.host + '/webscholar-web/websocket');

websocketSession.onmessage = function(e){

    console.log("mensagem recebida", e);

};
