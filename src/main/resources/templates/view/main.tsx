
import React from 'react'
import { motion } from 'framer-motion'
import {Brain, Users, Target, Zap, CheckCircle, Star, ArrowRight, Play, Award, TrendingUp, Clock, MessageSquare} from 'lucide-react'

function App() {
  const features = [
    {
      icon: Brain,
      title: "AI 면접관",
      description: "실제 면접관처럼 질문하고 피드백을 제공하는 AI 시스템"
    },
    {
      icon: Target,
      title: "맞춤형 질문",
      description: "직무와 경력에 맞는 개인화된 면접 질문 생성"
    },
    {
      icon: TrendingUp,
      title: "실시간 분석",
      description: "답변 내용, 말하기 속도, 자신감 등을 실시간으로 분석"
    },
    {
      icon: Award,
      title: "상세한 피드백",
      description: "면접 후 상세한 분석 리포트와 개선점 제안"
    }
  ]

  const stats = [
    { number: "10,000+", label: "누적 면접 연습" },
    { number: "95%", label: "만족도" },
    { number: "4.8", label: "평균 평점" },
    { number: "24/7", label: "언제든 이용" }
  ]

  const testimonials = [
    {
      name: "김민수",
      role: "소프트웨어 개발자",
      content: "실제 면접보다 더 까다로운 질문들로 연습할 수 있어서 실전에서 자신감이 생겼어요.",
      rating: 5
    },
    {
      name: "이지은",
      role: "마케팅 매니저",
      content: "AI 피드백이 정말 정확해요. 제가 놓치고 있던 부분들을 콕콕 짚어주더라고요.",
      rating: 5
    },
    {
      name: "박철호",
      role: "프로젝트 매니저",
      content: "바쁜 일정 중에도 언제든지 연습할 수 있어서 정말 편리합니다.",
      rating: 5
    }
  ]

  return (
    <div className="min-h-screen bg-white">
      {/* Header */}
      <header className="relative z-10">
        <nav className="container mx-auto px-6 py-6 flex items-center justify-between">
          <motion.div
            initial={{ opacity: 0, x: -20 }}
            animate={{ opacity: 1, x: 0 }}
            className="flex items-center space-x-3"
          >
            <div className="w-10 h-10 bg-gradient-to-r from-blue-300 to-purple-400 rounded-lg flex items-center justify-center">
              <Brain className="w-6 h-6 text-white" />
            </div>
            <span className="text-2xl font-bold text-gray-800">면접 시뮬레이터</span>
          </motion.div>

          <motion.div
            initial={{ opacity: 0, x: 20 }}
            animate={{ opacity: 1, x: 0 }}
            className="hidden md:flex items-center space-x-8"
          >
            <a href="#features" className="text-gray-600 hover:text-gray-800 transition-colors">기능</a>
            <a href="#testimonials" className="text-gray-600 hover:text-gray-800 transition-colors">후기</a>
            <button className="bg-gradient-to-r from-blue-300 to-purple-400 text-white px-6 py-2 rounded-full hover:shadow-lg transition-all">
              시작하기
            </button>
          </motion.div>
        </nav>
      </header>

      {/* Hero Section */}
      <section className="relative overflow-hidden py-20">
        {/* Blue Background Area - Full Width */}
        <motion.div
          initial={{ opacity: 0, y: 30 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.8 }}
          className="w-full bg-gradient-to-br from-blue-300 via-blue-400 to-purple-400 py-16 mb-16 relative overflow-hidden"
        >
          {/* Background decoration */}
          <div className="absolute inset-0 overflow-hidden pointer-events-none">
            <div className="absolute top-1/4 left-1/4 w-64 h-64 bg-white/10 rounded-full blur-3xl"></div>
            <div className="absolute top-3/4 right-1/4 w-96 h-96 bg-purple-200/10 rounded-full blur-3xl"></div>
          </div>

          {/* Content Container */}
          <div className="container mx-auto px-6 text-center relative z-10">
            <h1 className="text-2xl md:text-3xl font-bold text-white mb-6 leading-tight">
              AI와 함께하는
              <span className="bg-gradient-to-r from-blue-100 to-purple-100 bg-clip-text text-transparent">
                <br />완벽한 면접 연습
              </span>
            </h1>
            <p className="text-sm text-blue-100 mb-8 max-w-3xl mx-auto leading-relaxed">
              실제 면접관처럼 질문하고 피드백하는 AI 시스템으로
              언제 어디서나 실전같은 면접 연습을 경험하세요
            </p>

            <motion.div
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.8, delay: 0.2 }}
              className="flex justify-center"
            >
              <button className="group bg-white text-blue-400 px-8 py-4 rounded-full text-lg font-semibold hover:shadow-2xl transition-all flex items-center space-x-2">
                <Play className="w-5 h-5" />
                <span>지금 시작하기</span>
                <ArrowRight className="w-5 h-5 group-hover:translate-x-1 transition-transform" />
              </button>
            </motion.div>
          </div>
        </motion.div>

        {/* Stats - Outside blue background */}
        <div className="container mx-auto px-6">
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.8, delay: 0.4 }}
            className="grid grid-cols-2 md:grid-cols-4 gap-8 max-w-4xl mx-auto"
          >
            {stats.map((stat, index) => (
              <div key={index} className="text-center">
                <div className="text-3xl md:text-4xl font-bold text-gray-800 mb-2">{stat.number}</div>
                <div className="text-gray-600">{stat.label}</div>
              </div>
            ))}
          </motion.div>
        </div>
      </section>

      {/* Features Section */}
      <section id="features" className="py-20 bg-gray-50">
        <div className="container mx-auto px-6">
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
            className="text-center mb-16"
          >
            <h2 className="text-4xl md:text-5xl font-bold text-gray-800 mb-6">
              왜 면접 시뮬레이터인가요?
            </h2>
            <p className="text-xl text-gray-600 max-w-3xl mx-auto">
              최신 AI 기술로 실제 면접 환경을 완벽하게 재현하여
              효과적인 면접 준비를 도와드립니다
            </p>
          </motion.div>

          <div className="grid md:grid-cols-2 lg:grid-cols-4 gap-8">
            {features.map((feature, index) => (
              <motion.div
                key={index}
                initial={{ opacity: 0, y: 20 }}
                whileInView={{ opacity: 1, y: 0 }}
                viewport={{ once: true }}
                transition={{ delay: index * 0.1 }}
                className="bg-white rounded-2xl p-8 hover:shadow-lg transition-all group"
              >
                <div className="w-16 h-16 bg-gradient-to-r from-blue-300 to-purple-400 rounded-2xl flex items-center justify-center mb-6 group-hover:scale-110 transition-transform">
                  <feature.icon className="w-8 h-8 text-white" />
                </div>
                <h3 className="text-xl font-bold text-gray-800 mb-4">{feature.title}</h3>
                <p className="text-gray-600 leading-relaxed">{feature.description}</p>
              </motion.div>
            ))}
          </div>
        </div>
      </section>

      {/* How It Works */}
      <section className="py-20">
        <div className="container mx-auto px-6">
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
            className="text-center mb-16"
          >
            <h2 className="text-4xl md:text-5xl font-bold text-gray-800 mb-6">
              이렇게 간단해요
            </h2>
          </motion.div>

          <div className="grid md:grid-cols-3 gap-8 max-w-5xl mx-auto">
            {[
              {
                step: "01",
                title: "프로필 설정",
                description: "원하는 직무와 경력 수준을 설정하세요",
                icon: Users
              },
              {
                step: "02",
                title: "AI 면접 시작",
                description: "AI 면접관이 맞춤형 질문을 시작합니다",
                icon: MessageSquare
              },
              {
                step: "03",
                title: "피드백 받기",
                description: "상세한 분석과 개선점을 확인하세요",
                icon: CheckCircle
              }
            ].map((item, index) => (
              <motion.div
                key={index}
                initial={{ opacity: 0, y: 20 }}
                whileInView={{ opacity: 1, y: 0 }}
                viewport={{ once: true }}
                transition={{ delay: index * 0.2 }}
                className="text-center relative"
              >
                <div className="w-20 h-20 bg-gradient-to-r from-blue-300 to-purple-400 rounded-full flex items-center justify-center mx-auto mb-6">
                  <item.icon className="w-10 h-10 text-white" />
                </div>
                <div className="text-sm font-bold text-blue-400 mb-2">{item.step}</div>
                <h3 className="text-2xl font-bold text-gray-800 mb-4">{item.title}</h3>
                <p className="text-gray-600">{item.description}</p>

                {index < 2 && (
                  <div className="hidden md:block absolute top-10 left-full w-full">
                    <ArrowRight className="w-6 h-6 text-gray-400 mx-auto" />
                  </div>
                )}
              </motion.div>
            ))}
          </div>
        </div>
      </section>

      {/* Testimonials */}
      <section id="testimonials" className="py-20 bg-gray-50">
        <div className="container mx-auto px-6">
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
            className="text-center mb-16"
          >
            <h2 className="text-4xl md:text-5xl font-bold text-gray-800 mb-6">
              사용자들의 이야기
            </h2>
          </motion.div>

          <div className="grid md:grid-cols-3 gap-8 max-w-6xl mx-auto">
            {testimonials.map((testimonial, index) => (
              <motion.div
                key={index}
                initial={{ opacity: 0, y: 20 }}
                whileInView={{ opacity: 1, y: 0 }}
                viewport={{ once: true }}
                transition={{ delay: index * 0.1 }}
                className="bg-white rounded-2xl p-8 shadow-sm"
              >
                <div className="flex mb-4">
                  {[...Array(testimonial.rating)].map((_, i) => (
                    <Star key={i} className="w-5 h-5 text-yellow-400 fill-current" />
                  ))}
                </div>
                <p className="text-gray-600 mb-6 leading-relaxed">"{testimonial.content}"</p>
                <div>
                  <div className="font-bold text-gray-800">{testimonial.name}</div>
                  <div className="text-gray-500 text-sm">{testimonial.role}</div>
                </div>
              </motion.div>
            ))}
          </div>
        </div>
      </section>

      {/* CTA Section */}
      <section className="py-20">
        <div className="container mx-auto px-6 text-center">
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
            className="max-w-4xl mx-auto"
          >
            <h2 className="text-4xl md:text-5xl font-bold text-gray-800 mb-6">
              지금 바로 시작하세요
            </h2>
            <p className="text-xl text-gray-600 mb-8">
              무료로 면접 연습을 시작하고 꿈의 직장에 한 걸음 더 가까워지세요
            </p>
            <button className="group bg-gradient-to-r from-blue-300 to-purple-400 text-white px-12 py-4 rounded-full text-xl font-semibold hover:shadow-2xl transition-all flex items-center space-x-3 mx-auto">
              <Zap className="w-6 h-6" />
              <span>무료로 시작하기</span>
              <ArrowRight className="w-6 h-6 group-hover:translate-x-1 transition-transform" />
            </button>
          </motion.div>
        </div>
      </section>

      {/* Footer */}
      <footer className="bg-gray-800 py-12">
        <div className="container mx-auto px-6">
          <div className="flex flex-col md:flex-row justify-between items-center">
            <div className="flex items-center space-x-3 mb-4 md:mb-0">
              <div className="w-8 h-8 bg-gradient-to-r from-blue-300 to-purple-400 rounded-lg flex items-center justify-center">
                <Brain className="w-5 h-5 text-white" />
              </div>
              <span className="text-xl font-bold text-white">면접 시뮬레이터</span>
            </div>
            <div className="text-gray-400 text-sm">
              © 2025 면접 시뮬레이터. All rights reserved.
            </div>
          </div>
        </div>
      </footer>
    </div>
  )
}

export default App
