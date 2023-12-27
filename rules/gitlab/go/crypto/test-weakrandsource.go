// License: Apache 2.0 (c) gosec
// source: https://github.com/securego/gosec/blob/master/testutils/source.go
// hash: bfb0f42

package main

import (
	"math/rand"
	mrand "math/rand"
)

func bad1() {
	bad := rand.Int()
	println(bad)
}

func bad2() {
	good, _ := rand.Read(nil)
	println(good)
	bad := mrand.Int31()
	println(bad)
}

func badnewsource() {
	gen := rand.New(rand.NewSource(10))
	bad := gen.Int()
	println(bad)
}

func badIntn() {
	bad := rand.Intn(10)
	println(bad)
}
