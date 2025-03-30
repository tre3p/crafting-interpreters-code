.PHONY: clox

clean:
	rm -rf clox build

run:
	@make build_binary > /dev/null
	@./clox

build_binary:
	@make clean
	mkdir build
	cd build && cmake .. && cmake --build .
	mv build/clox .
	rm -rf build