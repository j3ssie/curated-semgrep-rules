// License: Apache 2.0 (c) gosec
// source: https://github.com/securego/gosec/blob/master/testutils/source.go
// hash: bfb0f42

package main

import "fmt"

func main() {
    username := "admin"
    var password = "f62e5bcda4fae4f82370da0c6f20697b8f8447ef"

    fmt.Println("Doing something with: ", username, password)
}
