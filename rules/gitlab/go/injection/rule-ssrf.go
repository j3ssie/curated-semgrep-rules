// License: Apache 2.0 (c) gosec
// source: https://github.com/securego/gosec/blob/master/testutils/source.go
// hash: bfb0f42

// Input from the std in is considered insecure
package main

import (
	"bufio"
	"bytes"
	"context"
	"fmt"
	"io/ioutil"
	"net/http"
	"os"
)

func foo1() {
	in := bufio.NewReader(os.Stdin)
	url, err := in.ReadString('\n')
	if err != nil {
		panic(err)
	}
	// ruleid:go_injection_rule-ssrf
	resp, err := http.Get(url)
	if err != nil {
		panic(err)
	}
	defer resp.Body.Close()
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		panic(err)
	}
	fmt.Printf("%s", body)
}

var url string = "https://www.google.com"

func foo2() {
	// ok:go_injection_rule-ssrf
	resp, err := http.Get(url)
	if err != nil {
		panic(err)
	}
	defer resp.Body.Close()
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		panic(err)
	}
	fmt.Printf("%s", body)
}

func foo3() {
	url := os.Getenv("tainted_url")
	// ruleid:go_injection_rule-ssrf
	resp, err := http.Get(url)
	if err != nil {
		panic(err)
	}
	defer resp.Body.Close()
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		panic(err)
	}
	fmt.Printf("%s", body)
}

const url1 = "http://127.0.0.1"

func foo4() {
	// ok:go_injection_rule-ssrf
	resp, err := http.Get(url1)
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(resp.Status)
}

func foo5() {
	var url string = "http://127.0.0.1"
	// ok:go_injection_rule-ssrf
	resp, err := http.Get(url)
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(resp.Status)
}

func foo6() {
	url := "http://127.0.0.1"
	// ok:go_injection_rule-ssrf
	resp, err := http.Get(url)
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(resp.Status)
}

func foo7() {
	url1 := "test"
	var url2 string = "http://127.0.0.1"
	url2 = url1
	// ok:go_injection_rule-ssrf
	resp, err := http.Get(url2)
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(resp.Status)
}

var Url string

func foo8() {
	// ruleid:go_injection_rule-ssrf
	resp, err := http.Get(Url)
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(resp.Status)
}

func get(url string) {
	// ruleid:go_injection_rule-ssrf
	resp, err := http.Get(url)
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(resp.Status)
}

func foo9() {
	url := "http://127.0.0.1"
	get(url)
}

func foo10() {
	var q = []byte(`your query`)
	// ok:go_injection_rule-ssrf
	req, err := http.NewRequest("POST", url, bytes.NewBuffer(q))
	req.Header.Set("X-Custom-Header", "myvalue")
	req.Header.Set("Content-Type", "text/plain")

	client := &http.Client{}
	client.Do(req)
}

func foo11() {
	// ok:go_injection_rule-ssrf
	http.Get("http://example.com")

	// ok:go_injection_rule-ssrf
	http.NewRequest("POST", "http://example.com", nil)

	// ok:go_injection_rule-ssrf
	http.NewRequestWithContext(context.TODO(), "POST", "http://example.com", nil)

	// ok:go_injection_rule-ssrf
	http.Post("http://example.com/upload", "image/jpeg", nil)
}

func foo12(u string) {
	// ruleid:go_injection_rule-ssrf
	http.Get(u)

	// ruleid:go_injection_rule-ssrf
	http.Head(u)

	// ruleid:go_injection_rule-ssrf
	http.NewRequest("POST", u, nil)

	// ruleid:go_injection_rule-ssrf
	http.NewRequestWithContext(context.TODO(), "POST", u, nil)

	// ruleid:go_injection_rule-ssrf
	http.Post(u, "image/jpeg", nil)
}

func foo13(u string) {

	// ruleid:go_injection_rule-ssrf
	http.DefaultClient.Get(u)

	// ruleid:go_injection_rule-ssrf
	http.DefaultClient.Head(u)

	// ruleid:go_injection_rule-ssrf
	http.DefaultClient.Post(u, "image/jpeg", nil)

	// ruleid:go_injection_rule-ssrf
	http.DefaultClient.PostForm(u, nil)

	safeUrl := "http://example.com/"

	// ok:go_injection_rule-ssrf
	http.DefaultClient.Get(safeUrl)

	// ok:go_injection_rule-ssrf
	http.DefaultClient.Head(safeUrl)

	// ok:go_injection_rule-ssrf
	http.DefaultClient.Post(safeUrl, "image/jpeg", nil)

	// ok:go_injection_rule-ssrf
	http.DefaultClient.PostForm(safeUrl, nil)

}
