HTMLPanel = Ext.extend(Ext.Panel, {
    constructor : function( config ) {
        HTMLPanel.superclass.constructor.apply(this, arguments);

        // load the html file with ajax when the item is
        // added to the parent container
        this.on(
            "added",
            function( panel, container, index ) {
                if( this.url && (this.url.length > 0) )
                {
                    Ext.Ajax.request({
                        url : this.url,
                        method : "GET",
                        success : function( response, request ) {
                            console.log("success -- response: "+response.responseText);
                            panel.update(response.responseText);
                        },
                        failure : function( response, request ) {
                            console.log("failed -- response: "+response.responseText);
                        }
                    });
                }
            },
            this
        )
    },

    url : null
});