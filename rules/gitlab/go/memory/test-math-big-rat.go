// License: Apache 2.0 (c) gosec
// source: https://github.com/securego/gosec/blob/master/testutils/source.go
// hash: bfb0f42
package main

import (
	"fmt"
	"math/big"
)

func bad1() {
	r := big.Rat{}
	r.SetString("13e-9223372036854775808")
	fmt.Println(r)
}

func bad2(input string) {
	r := big.Rat{}
	r.SetString(input)
	fmt.Println(r)
}
