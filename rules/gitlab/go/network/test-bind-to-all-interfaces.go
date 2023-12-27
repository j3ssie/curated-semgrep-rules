// License: Apache 2.0 (c) gosec
// source: https://github.com/securego/gosec/blob/master/testutils/source.go
// hash: bfb0f42

package main
import (
    "log"
    "net"
)
func main() {
    l, err := net.Listen("tcp", "0.0.0.0:2000")
    if err != nil {
        log.Fatal(err)
    }
    defer l.Close()
}

