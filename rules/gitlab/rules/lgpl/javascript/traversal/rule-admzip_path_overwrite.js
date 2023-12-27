// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/traversal/archive_path_overwrite.js
// hash: e7a0a61
//Ref: https://snyk.io/research/zip-slip-vulnerability
const fs = require('fs');
const unzip = require('unzip');

fs.createReadStream('archive.zip')
    .pipe(unzip.Parse())
    .on('entry', entry => {
        const fileName = entry.path;
        // Arbitrary file overwrite
        
        entry.pipe(fs.createWriteStream(fileName));
    });

fs.createReadStream('archive.zip')
    .pipe(unzip.Parse())
    .on('entry', entry => {
        const fileName = entry.path;
        // Arbitrary file overwrite
        
        entry.pipe(fs.writeFileSync(fileName));
    });

fs.readFile('path/to/archive.zip', function (err, zipContents) {
    unzip.Parse(zipContents).on('entry', function (entry) {
        var fileName = 'output/path/' + entry.path;
        // Arbitrary file overwrite
        
        fs.writeFileSync(fileName, entry.contents);
    });
});

//admzip
const fs = require('fs');
var AdmZip = require('adm-zip');
var zip = new AdmZip("archive.zip");
var zipEntries = zip.getEntries();
// ruleid:rules_lgpl_javascript_traversal_rule-admzip-path-overwrite
zipEntries.forEach(function (zipEntry) {
    fs.createWriteStream(zipEntry.entryName);
});

// ruleid:rules_lgpl_javascript_traversal_rule-admzip-path-overwrite
zip.getEntries().forEach(function (zipEntry) {
    fs.writeFileSync(zipEntry.entryName);
});

// tar-stream overwrite
const tar = require('tar-stream');
const extract = tar.extract();

extract.on('entry', (header, stream, next) => {
    
    const out = fs.createWriteStream(header.name);
    stream.pipe(out);
    stream.on('end', () => {
        next();
    })
    stream.resume();
})

tar.extract().on('entry', (header, stream, next) => {
    
    const out = fs.writeFileSync(header.name);
    stream.pipe(out);
    stream.on('end', () => {
        next();
    })
    stream.resume();
})

///unzipper lib
fs.createReadStream('./bad.tar').pipe(extract);
const fs = require('fs');
const unzipper = require('unzipper');

fs.createReadStream('path/to/archive.zip')
    .pipe(unzipper.Parse())
    .on('entry', function (entry) {
        var fileName = entry.path;
        
        entry.pipe(fs.createWriteStream(fileName));
    });
