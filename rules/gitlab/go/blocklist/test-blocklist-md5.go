// License: Apache 2.0 (c) gosec
// source: https://github.com/securego/gosec/blob/master/testutils/source.go
// hash: bfb0f42

package main

import (
	"crypto/md5"
	"fmt"
)

func mainMD55() {
	h := md5.New()
	h.Write([]byte("stuff"))
}

func mainMD5Sum() {
	out := md5.Sum([]byte("stuff"))
	fmt.Println(out)
}
