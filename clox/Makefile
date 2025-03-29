.PHONY: clox

clox:
	rm -rf clox build
	mkdir build
	cd build && cmake .. && cmake --build .
	mv build/clox .
	rm -rf build