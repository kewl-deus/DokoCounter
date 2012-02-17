Ext.regModel('Game', {
    hasMany : {model: 'Round', name: 'rounds'},

    newRound: function() {
        var r = Ext.ModelMgr.create({}, 'Round');
    }
});

Ext.regModel('Round', {
    fields: [
        {name: 'no', type: 'int'}
    ],
    belongsTo: 'Game',
    hasMany: {model: 'Score', name: 'scores'}
});

Ext.regModel('Score', {
   fields: [
       {name: 'playerName', type: 'string'},
       {name: 'points', type: 'int'}
   ]
});

Ext.regModel('Team', {
   hasMany: {model:'Player', name:'players'}
});

Ext.regModel('Player', {
    fields:[
        {name:'name', type:'string'}
    ]
});




/*

var game = {
    players : ["p1", "p2", "p3", "p4"],
    rounds : [
        {
            scores: [
                {"p1" : 2},
                {"p2" : 2},
                {"p3" : 0},
                {"p4" : 0}
            ],
            teamRe : ["p1", "p2"],
            teamContra: ["p3", "p4"]
        }
    ]
}


class Game {
    string[] players;
    Round[] rounds;

    addPlayer(string name)

    newRound()
}

class Round {
    int no;
    Map[string -> int] scores; //playername -> points

    //teams: exception solo!
    string[] teamRe;
    string[] teamContra;

    setScore(string player, int points)
}
*/


