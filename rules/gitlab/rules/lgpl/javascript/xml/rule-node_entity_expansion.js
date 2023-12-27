// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/xml/xml_entity_expansion_dos.js
// hash: e7a0a61
app.get('/expat', function (req, res) {
    // ruleid:rules_lgpl_javascript_xml_rule-node-entity-expansion
    var parser = new expat.Parser();
    parser.write(req.param("xml"));
})
