new Ext.Application({
    name: 'Doppelkopf-Z&auml;hler',
    launch: test
});

function test(){

    Ext.define("Score", {
       extends: "Ext.data.Model",
        fields: [
            {name: 'playerName', type: 'string'},
            {name: 'points', type: 'int'}
        ]
    });


    var scoreStore = new Ext.data.Store({
        model: 'Score',
        proxy: {
            type: 'sessionstorage',
            id  : 'scoreStore'
        }
    });

    var score1 = Ext.ModelMgr.create({
        playerName: 'Spieler1',
        points: 12
    }, 'Score');
    var score2 = Ext.ModelMgr.create({
        playerName: 'Spieler2',
        points: 3
    }, 'Score');

    scoreStore.add(score1, score2);

    var scoreView = new Ext.DataView({
        store : scoreStore,
        tpl: new Ext.XTemplate(
                
        ),
        fullscreen: true
    });
}

function main() {


    var buttonKing = new Ext.Button({
        //icon: 'resources/img/cards/karo_koenig.png'
        //text: 'Karo Koenig'
        html: '<img src="resources/img/cards/karo_koenig.png" />'
    });

    var buttonQueen = new Ext.Button({
        html: '<img src="resources/img/cards/karo_dame.png" />'
    });

    var panelCards = new Ext.Panel({
        dockedItems : [{xtype:'toolbar', title:'Z&auml;hlmaschine'}],
        layout: {
            type : 'vbox',
            pack : 'top',
            align: 'left'
        },
        items: [buttonKing, buttonQueen]
    });

    var panelScores = new Ext.Panel({
        dockedItems : [{xtype:'toolbar', title:'Spielstand'}],
        layout: {
            type: 'vbox',
            pack: 'top',
            align: 'right'
        }
    })

    var rootPanel = new Ext.Panel({
        fullscreen: true,
        dockedItems : [{xtype:'toolbar', title:'Doppelkopf'}],
        layout: 'fit',
        items: [panelCards, panelScores]
    });
}