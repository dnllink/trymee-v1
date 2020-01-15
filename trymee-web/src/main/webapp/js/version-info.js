$(document).ready(function(){

	$.getJSON("js/git.js", function(json) {
	    var commit_time = 'commit time: ' + json['git.commit.time'];
		var build_time = 'build time: ' + json['git.build.time'];
		var commit_id = 'commit id: ' + json['git.commit.id.abbrev'];
		$("#version-info").val(commit_time + ' - ' + build_time + ' - ' + commit_id);
	});

});