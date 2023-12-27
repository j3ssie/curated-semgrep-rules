// License: MIT (c) GitLab Inc.

package main

import (
	net "net/http"
)

func dtrav() {
	d := net.Dir("/")
	f, err := d.Open("some file")
	if err != nil {
		panic(err)
	}
	defer f.Close()
}
