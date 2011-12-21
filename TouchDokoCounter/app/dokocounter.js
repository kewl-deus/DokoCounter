new Ext.Application({
    launch: main
});


function main() {


    var buttonKoenig = new Ext.Button({
        //icon: 'resources/img/cards/karo_koenig.png'
        //text: 'Karo Koenig'
        html: '<img src="resources/img/cards/karo_koenig.png" />'
    });

    var panel = new Ext.Panel({
        layout: {
            type : 'vbox',
            pack : 'center',
            align: 'stretch'
        },
        items: [buttonKoenig]
    });

    var rootPanel = new Ext.Panel({
        fullscreen: true,
        items: [panel]
    });
}