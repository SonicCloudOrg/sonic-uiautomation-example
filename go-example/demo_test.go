package go_example

import (
	"github.com/electricbubble/guia2"
	"testing"
)

var uiaServerURL = "http://192.168.0.9:7777/uia/55286"

func Test_demo(t *testing.T) {
	guia2.SetDebug(true)
	driver, err := guia2.NewDriver(nil, uiaServerURL)
	if err != nil {
		t.Fatal(err)
	}
	source, err := driver.Source()
	if err != nil {
		t.Fatal(err)
	}
	t.Log(source)
}
