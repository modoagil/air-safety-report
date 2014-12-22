var gulp = require('gulp');
var bower = require('gulp-bower');
var sass = require('gulp-ruby-sass');
var uglify = require('gulp-uglify');
var minifyCss = require('gulp-minify-css');

var config = {
    sassPath: './resources/sass',
    bowerDir: './bower_components'
}

gulp.task('bower', function() {
    return bower().pipe(gulp.dest(config.bowerDir))
});

gulp.task('watch', function() {
    gulp.watch(config.sassPath + '/**/*.scss', ['css']);
});

gulp.task('default', ['bower']);
