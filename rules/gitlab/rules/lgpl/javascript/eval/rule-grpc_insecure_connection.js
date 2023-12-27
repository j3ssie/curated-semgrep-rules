// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/eval/eval_grpc_deserialize.js
// hash: e7a0a61
function test1() {
    var grpc = require('grpc');

    var booksProto = grpc.load('books.proto');

    // ruleid:rules_lgpl_javascript_eval_rule-grpc-insecure-connection
    var client = new booksProto.books.BookService('127.0.0.1:50051', grpc.credentials.createInsecure());

    client.list({}, function (error, books) {
        if (error)
            console.log('Error: ', error);
        else
            console.log(books);
    });
}

function test2() {
    var { credentials, load, Client } = require('grpc');

    // ruleid:rules_lgpl_javascript_eval_rule-grpc-insecure-connection
    var creds = someFunc() || credentials.createInsecure();

    var client = new Client('127.0.0.1:50051', creds);

    client.list({}, function (error, books) {
        if (error)
            console.log('Error: ', error);
        else
            console.log(books);
    });
}

function test3() {
    var grpc = require('grpc');

    var booksProto = grpc.load('books.proto');

    var server = new grpc.Server();

    server.addProtoService(booksProto.books.BookService.service, {});

    // ruleid:rules_lgpl_javascript_eval_rule-grpc-insecure-connection
    server.bind('0.0.0.0:50051', grpc.ServerCredentials.createInsecure());
    server.start();
}
