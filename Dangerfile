# Message
message "Thank you so much for your work here @#{github.pr_author} 🎉 You might find a few suggestions from me for this Pull Request below 🙂"

#warning
warn("I noticed this is marked as Work In Progress, does it need to be open before it is ready? 🙂") if github.pr_title.include? "[WIP]"
warn("You should provide a summary in the Pull Request description so that the reviewer has more context on this Pull Request 🤔") if github.pr_body.length < 5
warn("This PR is quite a big one! Maybe try splitting this into separate tasks next time 🙂") if git.lines_of_code > 600

# ktlint
checkstyle_format.base_path = Dir.pwd
checkstyle_format.report "/bitrise/src/app/build/reports/ktlint/ktlintMainSourceSetCheck/ktlintMainSourceSetCheck.html"

# android lint
android_lint.skip_gradle_task = true
android_lint.filtering = true
Dir["**/build/reports/lint-results*.xml"].each do |file|
  android_lint.report_file = file
  android_lint.lint(inline_mode: true)
end
