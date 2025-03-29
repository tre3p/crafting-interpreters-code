.PHONY: clox

run:
	@make build_binary > /dev/null
	@./clox

build_binary:
	rm -rf clox build
	mkdir build
	cd build && cmake .. && cmake --build .
	mv build/clox .
	rm -rf build