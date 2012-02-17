Ext.application({
    name: "Testing",
    launch: runTest
});


function runTest() {

    /*
    var htmlPanel = new HTMLPanel({
        //scroll : "vertical",
        id: "svgPanel",
        title   : "my html panel",
        url     : "/resources/svg/cards/Playing_card_club_K.svg",
        renderTo: "svgBox",
        width: 50,
        height: 100
    });
*/

    var svgPanel = new Ext.Panel({
        id: 'svgPanel',
        html: "<iframe src='resources/svg/cards/Playing_card_club_K.svg' width='100%' height='100%' id='svgPanel-svgFrame'><p>Your browser does not support iframes.</p> </iframe>",
        width: 50,
        height: 100,
        renderTo: 'svgBox'
    });

    scaleSvg('svgPanel-svgFrame', 100, 125);


/*
    var panel = new Ext.Panel({
        id: 'svgPanel',
        html: 'Das ist ein Test',
        renderTo: "svgBox"
    });
*/

    /*
    var svgComp = new Ext.draw.Component({
        id: 'svgComp',
        width: 200,
        height: 300
    });

    var svgHolder = new Ext.panel.Panel({
        id: 'svgHolder',
        title: 'SVG Holder',
        renderTo: 'svgBox',
        items:  [svgComp]
    });


    var svgAss = Ext.get('svgCacheA').dom.innerHTML;


    var svgHolderDom = Ext.get('svgHolderId').dom;
    svgHolderDom.innerHTML = svgAss;
      */

};

function swapSvg(){
    console.log('switching content of svgHolder');

    var svgBube = Ext.get('svgCacheJ').dom.innerHTML;
    Ext.getCmp('svgPanel').setHtml(svgBube);


    //var svgHolderDom = Ext.get('svgHolderId').dom;
    //svgHolderDom.innerHTML = svgBube;
};