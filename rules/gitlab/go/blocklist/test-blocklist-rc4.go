// License: Apache 2.0 (c) gosec
// source: https://github.com/securego/gosec/blob/master/testutils/source.go
// hash: bfb0f42

package main

import (
	"crypto/rc4"
	"fmt"
)

func mainrc4() {
	k := []byte("super secret key")
	c, err := rc4.NewCipher(k)
	if err != nil {
		return
	}
	out := make([]byte, 0)
	in := []byte("some cleartext")
	c.XORKeyStream(out, in)
	fmt.Printf("Doing something with our data: %v", out)
}
