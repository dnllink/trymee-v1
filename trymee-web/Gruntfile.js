module.exports = function (grunt) {

	grunt.initConfig({
		copy: {
			project: {
				expand: true,
				cwd: 'src/main/webapp',
				src: ['**', '!Gruntfile.js', '!package.json', '!bower.json'],
				dest: 'dist'
			}
		},
		clean: {
			dist: {
				src: 'dist'
			}
		},
		usemin: {
			html: 'dist/index.html'
		},
		useminPrepare: {
			options: {
				root: 'dist',
				dest: 'dist'
			},
			html: 'dist/index.html'
		},
		ngAnnotate: {
			scripts: {
				expand: true,
				src: 'dist/js/**/*.js'
			}
		}
	});

	grunt.registerTask('dist', ['clean', 'copy']);
	grunt.registerTask('minify', ['useminPrepare', 'ngAnnotate', 'concat', 'uglify', 'cssmin', 'usemin']);
	grunt.registerTask('default', ['dist', 'minify']);

	grunt.loadNpmTasks('grunt-contrib-copy');
	grunt.loadNpmTasks('grunt-contrib-clean');
	grunt.loadNpmTasks('grunt-contrib-concat');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-cssmin');
	grunt.loadNpmTasks('grunt-usemin');
	grunt.loadNpmTasks('grunt-ng-annotate');

}