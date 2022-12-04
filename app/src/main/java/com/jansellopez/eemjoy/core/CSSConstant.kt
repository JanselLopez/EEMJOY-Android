package com.jansellopez.eemjoy.core

object CSSConstant {
    //CSS
    val css = "body {\n" +
            "\tmargin: 10px; padding: 0;\n" +
            "\tbackground-image: url(../img/fondo.png);\n" +
            "\tbackground-repeat: repeat;\n" +
            "\tpadding-bottom: 0px;\n" +
            "\tfont-size: 11px;\n" +
            "\t\n" +
            "}\n" +
            "body, td, th {\n" +
            "\tfont-family: Arial, Helvetica, sans-serif;\n" +
            "\tfont-size:12px;\n" +
            "}\n" +
            "a:link    { color: #0B55C4; text-decoration: none; }\n" +
            "a:visited { color: #0B55C4; text-decoration: none; }\n" +
            "a:hover   { text-decoration: underline; }\n" +
            "/*---------------Juego de Campos----------------------*/\n" +
            "fieldset\n" +
            "{\n" +
            "    margin-bottom: 10px;\n" +
            "    border: 1px #ccc solid;\n" +
            "    padding: 5px;\n" +
            "    text-align: left;\n" +
            "}\n" +
            "\n" +
            "fieldset p {  margin: 10px 0px;  }\n" +
            "\n" +
            "legend    {\n" +
            "    color: #0B55C4;\n" +
            "    font-size: 12px;\n" +
            "    font-weight: bold;\n" +
            "}\n" +
            "fieldset.adminform { border: 1px solid #ccc; margin: 0 10px 10px 10px; }\n" +
            "\n" +
            "/*------------Tablas de la administracion------------*/\n" +
            "table.adminlist {\n" +
            "    width: 100%;\n" +
            "    border-spacing: 1px;\n" +
            "    background-color: #e7e7e7;\n" +
            "    color: #666;\n" +
            "\tfont-size:12px;\n" +
            "}\n" +
            "table.adminlist td,\n" +
            "table.adminlist th { padding: 4px; }\n" +
            "\n" +
            "table.adminlist thead th {\n" +
            "    text-align: center;\n" +
            "    background: #f0f0f0;\n" +
            "    color: #666;\n" +
            "    border-bottom: 1px solid #999;\n" +
            "    border-left: 1px solid #fff;\n" +
            "}\n" +
            "table.adminlist thead a:hover { text-decoration: none; }\n" +
            "table.adminlist thead th img { vertical-align: middle; }\n" +
            "table.adminlist tbody th { font-weight: bold; }\n" +
            "table.adminlist tbody tr            { background-color: #fff;  text-align: left; }\n" +
            "table.adminlist tbody tr.row1   { background: #f9f9f9; border-top: 1px solid #fff; }\n" +
            "table.adminlist tbody tr.row0:hover td,\n" +
            "table.adminlist tbody tr.row1:hover td  { background-color: #ffd ; }\n" +
            "table.adminlist tbody tr td        { height: 30px; background: #fff; border: 1px solid #fff;}\n" +
            "table.adminlist tbody tr.row1 td { background: #f9f9f9; border-top: 1px solid #FFF; }\n" +
            "table.adminlist tfoot tr { text-align: center;  color: #333; }\n" +
            "table.adminlist tfoot td,\n" +
            "table.adminlist tfoot th { background-color: #f3f3f3; border-top: 1px solid #999; text-align: center; }\n" +
            "/*---------------------------------------------------*/\n" +
            "/* standard form style table */\n" +
            "table.admintable td \t\t\t\t\t { padding: 3px; }\n" +
            "table.admintable td.key,\n" +
            "table.admintable td.paramlist_key {\n" +
            "\tbackground-color: #f6f6f6;\n" +
            "\ttext-align: right;\n" +
            "\twidth: 140px;\n" +
            "\tcolor: #666;\n" +
            "\tfont-weight: bold;\n" +
            "\tfont-size: 12px;\n" +
            "\tborder-bottom: 1px solid #e9e9e9;\n" +
            "\tborder-right: 1px solid #e9e9e9;\n" +
            "}\n" +
            "\n" +
            "table.paramlist td.paramlist_description {\n" +
            "\tbackground-color: #f6f6f6;\n" +
            "\ttext-align: left;\n" +
            "\twidth: 170px;\n" +
            "\tcolor: #333;\n" +
            "\tfont-weight: normal;\n" +
            "\tborder-bottom: 1px solid #e9e9e9;\n" +
            "\tborder-right: 1px solid #e9e9e9;\n" +
            "}\n" +
            "table.admintable td.key.vtop { vertical-align: top; }\n" +
            "\n" +
            "table.adminform {\n" +
            "\tbackground-color: #f9f9f9;\n" +
            "\tborder: solid 1px #d5d5d5;\n" +
            "\twidth: 100%;\n" +
            "\tborder-collapse: collapse;\n" +
            "\tmargin: 8px 0 10px 0;\n" +
            "\tmargin-bottom: 15px;\n" +
            "\twidth: 100%;\n" +
            "}\n" +
            "table.adminform.nospace { margin-bottom: 0; }\n" +
            "table.adminform tr.row0 { background-color: #f9f9f9; }\n" +
            "table.adminform tr.row1 { background-color: #eeeeee; }\n" +
            "\n" +
            "table.adminform th {\n" +
            "\tfont-size: 11px;\n" +
            "\tpadding: 6px 2px 4px 4px;\n" +
            "\ttext-align: left;\n" +
            "\theight: 25px;\n" +
            "\tcolor: #000;\n" +
            "\tbackground-repeat: repeat;\n" +
            "}\n" +
            "table.adminform td { padding: 3px; text-align: left; }\n" +
            "\n" +
            "table.adminform td.filter{\n" +
            "\ttext-align: left;\n" +
            "}\n" +
            "\n" +
            "table.adminform td.helpMenu{\n" +
            "\ttext-align: right;\n" +
            "}\n" +
            "fieldset.adminform { border: 1px solid #ccc; margin: 0 10px 10px 10px; }\n" +
            "/* Blocks */\n" +
            ".block{\n" +
            "\twidth:1000px;\n" +
            "\tpadding-bottom: 5px;\n" +
            "\tmargin-bottom: 25px;\n" +
            "\tclear: both;\n" +
            "\tborder-radius: 10px;\n" +
            "\t/*background: #000 url(../img/bnd.gif) bottom center repeat-x;*/\n" +
            "\tbackground-color:#fff;\n" +
            "\tborder: solid 1px #CCCCCC;\n" +
            "}\n" +
            "/* Contenedor*/\n" +
            "\n" +
            ".wrapper {\n" +
            "\twidth:1000px;\n" +
            "\n" +
            "\tmargin:0 auto;\n" +
            "\tpadding-top: 20px;\n" +
            "} \n" +
            "\n" +
            "/* Block Content*/\n" +
            ".block .block_content {\n" +
            "\toverflow: hidden;\n" +
            "\tbackground: #fff;\n" +
            "\twidth:960px;\n" +
            "\t/*border-left: 1px solid #ccc;\n" +
            "\tborder-right: 1px solid #ccc;*/\n" +
            "\tborder-top: 1px solid #CECECE;\n" +
            "\tpadding: 10px 20px 10px;\n" +
            "}\n" +
            ".block .block_content_1 {\n" +
            "\tbackground: #FC9;\n" +
            "}\n" +
            ".block .block_content_2 {\n" +
            "\tbackground:#3CF;\n" +
            "}\n" +
            "/* Block head */\n" +
            "\n" +
            ".block .block_head {\n" +
            "\theight:62px;\n" +
            "\tbackground-color:#F5F5F5;\n" +
            "\t-moz-border-radius: 10px 10px 0px 0px;\n" +
            "\t-o-border-radius: 10px 10px 0px 0px;\n" +
            "\t-webkit-border-radius: 10px 10px 0px 0px;\n" +
            "\t-ms-border-radius: 10px 10px 0px 0px;\n" +
            "\n" +
            "\toverflow:visible;\t\t\n" +
            "\t\n" +
            "\t}\n" +
            "/* Tool bar */\n" +
            ".block .tool_bar {\n" +
            "\t\n" +
            "}\n" +
            ".block .tool_bar ul li {\n" +
            "\tdisplay: block;\n" +
            "\tbackground:none;\n" +
            "\tfloat:left;\n" +
            "\tpadding-left:10px;\n" +
            "}\n" +
            ".block .tool_bar ul li a:hover{\n" +
            "\tbackground-color: #FFF;\n" +
            "\tborder: 1px solid #999;\n" +
            "\tcolor: #333;\n" +
            "}\n" +
            ".block .titulo_head {\n" +
            "\theight:25px;\n" +
            "\twidth:auto;\n" +
            "\tfloat:left;\n" +
            "\tfont-family: \"Titillium999\", \"Trebuchet MS\", Arial, sans-serif;\n" +
            "\tfont-size: 18px;\n" +
            "\tfont-weight: normal;\n" +
            "\ttext-transform: uppercase;\n" +
            "\tcolor: #555;\n" +
            "\ttext-shadow: 1px 1px 0 #fff;\n" +
            "\tmargin-left: 15px;\n" +
            "\tpadding-top:20px;\n" +
            "}\n" +
            ".block .imagen_head {\n" +
            "\theight:25px;\n" +
            "\twidth:50;\n" +
            "\tfloat:left;\n" +
            "\tmargin-left: 20px;\n" +
            "\tpadding-top:10px;\n" +
            "}\n" +
            ".block .menu_head {\n" +
            "\theight:20px;\n" +
            "\t}\n" +
            "/* ---------------------------------------\n" +
            "\t\tICON DOCK\n" +
            "----------------------------------------- */\n" +
            "\n" +
            "#icon ul{ padding-left:1px}\n" +
            "#icon ul li a img { margin-bottom: 1px;} /*distancia entre texto e imagen*/\n" +
            "\n" +
            "#icon ul li a {\n" +
            "\tfloat: left;\n" +
            "\theight: 40px;\n" +
            "\twidth: 40px;\n" +
            "\tpadding-bottom:10px;\n" +
            "\n" +
            "\tposition: relative;\n" +
            "\ttext-decoration: none;\n" +
            "\tborder: 1px solid #CCC;\n" +
            "\tbackground-color: #F2F2F2;\n" +
            "\tmargin-bottom: 20px;\n" +
            "\tmargin-left: 0px;\n" +
            "\ttext-align: center;\n" +
            "\tcolor: #666;\n" +
            "\tfont-size: 11px;\n" +
            "\tfont-weight: bold;\n" +
            "\t\n" +
            "\t/*Mozilla Browsers Only */\n" +
            "\t-moz-border-radius: 5px;\t\n" +
            "\t\n" +
            "}\n" +
            "#icon ul li {\n" +
            "\tdisplay: block;\n" +
            "\tbackground:none;\n" +
            "\tfloat:left;\n" +
            "\tpadding-left:10px;\n" +
            "}\n" +
            "#icon ul li a:hover{\n" +
            "\tbackground-color: #FFF;\n" +
            "\tborder: 1px solid #999;\n" +
            "\tcolor: #333;\n" +
            "}\n" +
            "/* Inicio - Editar */\n" +
            ".operaciones {\n" +
            "\tfont-family: Arial, Helvetica, sans-serif;\n" +
            "\tfont-size:12px;\n" +
            "\tfont-weight:bold;\n" +
            "\ttext-align:center;\n" +
            "}\n" +
            ".operaciones a:link    { color: #0B55C4; text-decoration: none; }\n" +
            ".operaciones a:visited { color: #0B55C4; text-decoration: none; }\n" +
            ".operaciones a:hover   { text-decoration: underline; }\n" +
            "\t\n" +
            "/** toolbar **/\n" +
            "div.toolbar\n" +
            "{ \n" +
            "float: right; \n" +
            "text-align: right; \n" +
            "padding: 0;\n" +
            "margin-right: 20px;\n" +
            "margin-top:5px;\n" +
            "}\n" +
            "table.toolbar    \t\t\t { border-collapse: collapse; padding: 0; margin: 0;\t }\n" +
            "table.toolbar td \t\t\t { padding: 1px 1px 1px 4px; text-align: center; color: #666; height: 48px; }\n" +
            "table.toolbar td.spacer  { width: 10px; }\n" +
            "table.toolbar td.divider { border-right: 1px solid #eee; width: 5px; }\n" +
            "table.toolbar span { float: none; width: 32px; height: 32px; margin: 0 auto; display: block; }\n" +
            "table.toolbar a {\n" +
            "    display: block; float: left;\n" +
            "\tbackground:#F5F5F5;\n" +
            "\twhite-space: nowrap;\n" +
            "\tborder: 1px solid #F5F5F5;\n" +
            "\tpadding: 1px 5px;\n" +
            "\tcursor: pointer;\n" +
            "\tfont-size:11px;\n" +
            "}\n" +
            "table.toolbar a:hover {\n" +
            "\tborder-left: 1px solid #eee;\n" +
            "\tborder-top: 1px solid #eee;\n" +
            "\tborder-right: 1px solid #ccc;\n" +
            "\tborder-bottom: 1px solid #ccc;\n" +
            "\ttext-decoration: none;\n" +
            "\tcolor: #0B55C4;\n" +
            "}\n" +
            "/** button **/\n" +
            ".button{\n" +
            "\tdisplay: block; float: left;\n" +
            "\twhite-space: nowrap;\n" +
            "\tborder: 1px solid #F5F5F5;\n" +
            "\tpadding: 1px 5px;\n" +
            "\tcursor: pointer;\n" +
            "\tfont-family: Arial, Helvetica, sans-serif;\n" +
            "\tfont-size:11px;\n" +
            "\tcolor: #0B55C4;\n" +
            "\ttext-align:center center;\n" +
            "\tbackground:#F5F5F5;\n" +
            "}\n" +
            ".button:hover {\n" +
            "\tborder-left: 1px solid #eee;\n" +
            "\tborder-top: 1px solid #eee;\n" +
            "\tborder-right: 1px solid #ccc;\n" +
            "\tborder-bottom: 1px solid #ccc;\n" +
            "\ttext-decoration: none;\n" +
            "\tcolor: #0B55C4;\n" +
            "}\n" +
            "/* Estilo por defecto para validacion */  \n" +
            "#input:required:invalid {  border: 1px solid red;}  \n" +
            "\n" +
            "#input:required:valid {  border: 1px solid green;  }\n" +
            "/*------------- Divisiones---------------- */\n" +
            ".zona_total{\n" +
            "width:400px;\n" +
            "float:left;\n" +
            "margin-left:50px;\n" +
            "}\n" +
            ".zona_impresion{\n" +
            "width: 360px;\n" +
            "float:left;\n" +
            "margin-left:00px;\n" +
            "}\n" +
            ".box_contado{\n" +
            "background-repeat:repeat;\n" +
            "padding:10px 10px 10px 10px;\n" +
            "border:0px solid #d2d3d4;\n" +
            "border-bottom-color:#d2d3d4;\n" +
            "border-radius:6px;\n" +
            "box-shadow:0 2px 2px -2px rgba(0,0,0,0.2)\n" +
            "}\n" +
            ".box_contado_texto{\n" +
            "text-align:center;\n" +
            "font-size:16px;\n" +
            "font-weight:bold;\n" +
            "color:#0B55C4;\n" +
            "padding:10px 10px 10px 10px;\n" +
            "border:1px solid #d2d3d4;\n" +
            "border-bottom-color: #CCC;\n" +
            "\n" +
            "background-color: #EAEAEA;\n" +
            "box-shadow:0 2px 2px -2px rgba(0,0,0,0.2);\n" +
            "\n" +
            "  border-top: 1px solid #ddd;\n" +
            "  -webkit-border-radius: 6px 6px 0px 0px;\n" +
            "     -moz-border-radius: 6px 6px 0px 0px;\n" +
            "          border-radius: 6px 6px 0px 0px;\n" +
            "}\n" +
            ".box_contado_dato{\n" +
            "\t\n" +
            "text-align:center;\n" +
            "font-size:20px;\n" +
            "font-weight:bold;\n" +
            "padding:10px 10px 10px 10px;\n" +
            "border:1px solid #d2d3d4;\n" +
            "border-bottom-color:#afb0b1;\n" +
            "background-color: #FFF;\n" +
            "box-shadow:0 2px 2px -2px rgba(0,0,0,0.2);\n" +
            "  border-top: 1px solid #ddd;\n" +
            "  -webkit-border-radius: 0px 0px 6px 6px;\n" +
            "     -moz-border-radius: 0px 0px 6px 6px;\n" +
            "          border-radius: 0px 0px 6px 6px;\n" +
            "}\n" +
            ".ticket_body td,.thead th {\n" +
            "\tfont-size:15px;\n" +
            "}\n" +
            ".headers td{\n" +
            "\tfont-size: 14px;\n" +
            "}"
}